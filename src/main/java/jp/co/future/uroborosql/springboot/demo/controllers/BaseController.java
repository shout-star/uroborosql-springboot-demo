package jp.co.future.uroborosql.springboot.demo.controllers;

import jp.co.future.uroborosql.SqlAgent;
import jp.co.future.uroborosql.config.DefaultSqlConfig;
import jp.co.future.uroborosql.config.SqlConfig;
import jp.co.future.uroborosql.filter.DebugSqlFilter;
import jp.co.future.uroborosql.springboot.demo.models.BaseModel;
import jp.co.future.uroborosql.utils.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * BaseController
 *
 * @author Kenichi Hoshi
 */
public abstract class BaseController {
    private final DataSource dataSource;

    @Autowired
    public BaseController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * create <code>SqlAgent</code> instance.
     *
     * @return <code>SqlAgent</code>
     */
    SqlAgent createAgent() {
        try {
            SqlConfig config = DefaultSqlConfig.getConfig(dataSource.getConnection());

            config.getSqlFilterManager().addSqlFilter(new DebugSqlFilter());
            config.getSqlFilterManager().initialize();

            return config.createAgent();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * get generate keys.
     *
     * @param agent SqlAgent
     * @return keys as {@literal Map<String, Object>}
     * @throws SQLException SQLException
     */
    Map<String, Object> generatedKeys(SqlAgent agent) throws SQLException {
        return agent.queryWith("SELECT SCOPE_IDENTITY() AS ID")
            .collect(CaseFormat.CamelCase)
            .get(0);
    }

    protected Map<String, Object> handleCreate(BaseModel model) throws SQLException {
        try (SqlAgent agent = createAgent()) {
            Map<String, Object> result = new HashMap<>();

            agent.required(() -> {
                agent.insert(model);
                result.putAll(generatedKeys(agent));
            });
            return result;
        }
    }

    protected void handleUpdate(int id, BaseModel owner) throws SQLException {
        try (SqlAgent agent = createAgent()) {
            agent.required(() -> {
                owner.setId(id);
                agent.update(owner);
            });
        }
    }
}
