package jp.co.future.uroborosql.springboot.demo.controllers;

import jp.co.future.uroborosql.SqlAgent;
import jp.co.future.uroborosql.config.SqlConfig;
import jp.co.future.uroborosql.springboot.demo.common.context.AuthContext;
import jp.co.future.uroborosql.springboot.demo.models.BaseModel;
import jp.co.future.uroborosql.utils.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * BaseController
 *
 * @author Kenichi Hoshi
 */
public abstract class BaseController {
    @Autowired
    private SqlConfig sqlConfig;

    /**
     * create <code>SqlAgent</code> instance.
     *
     * @return <code>SqlAgent</code>
     */
    SqlAgent createAgent() {
        return sqlConfig.createAgent();
    }

    /**
     * get generate keys.
     *
     * @param agent SqlAgent
     * @return keys as {@literal Map<String, Object>}
     */
    private Map<String, Object> generatedKeys(SqlAgent agent) {
        return agent.queryWith("SELECT SCOPE_IDENTITY() AS ID")
            .collect(CaseFormat.CAMEL_CASE)
            .get(0);
    }

    Map<String, Object> handleCreate(BaseModel model) {
        int userId = getCurrentUserId();
        try (SqlAgent agent = createAgent()) {
            return agent.required(() -> {
                model.setCreatorId(userId);
                model.setUpdaterId(userId);
                model.setCreatedAt(new Date());
                model.setUpdatedAt(new Date());
                agent.insert(model);
                return generatedKeys(agent);
            });
        }
    }

    int handleUpdate(int id, BaseModel model) {
        int userId = getCurrentUserId();
        try (SqlAgent agent = createAgent()) {
            return agent.required(() -> {
                model.setId(id);
                model.setUpdaterId(userId);
                model.setUpdatedAt(new Date());
                return agent.update(model);
            });
        }
    }

    /**
     * get current user id.
     *
     * @return user id
     */
    private int getCurrentUserId() {
        return Integer.parseInt(String.valueOf(AuthContext.getClaims().get("userId")));
    }

}
