package jp.co.future.uroborosql.springboot.demo.controllers;

import jp.co.future.uroborosql.SqlAgent;
import jp.co.future.uroborosql.utils.CaseFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/types")
public class TypeController extends BaseController {

    public TypeController(DataSource dataSource) {
        super(dataSource);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Map<String, Object>> all() throws SQLException {
        try (SqlAgent agent = createAgent()) {
            return agent.query("types-all")
                .collect(CaseFormat.CamelCase);
        }
    }
}
