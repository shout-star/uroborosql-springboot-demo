package jp.co.future.uroborosql.springboot.demo.controllers;

import jp.co.future.uroborosql.SqlAgent;
import jp.co.future.uroborosql.utils.CaseFormat;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/vets")
public class VetController extends BaseController {

    public VetController(DataSource dataSource) {
        super(dataSource);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Map<String, Object>> all() throws SQLException {
        try (SqlAgent agent = createAgent()) {
            return agent.query("vets-all")
                .collect(CaseFormat.CamelCase);
        }
    }

    @RequestMapping(value = { "/vets.json", "/vets.xml" })
    public @ResponseBody List<Map<String, Object>> allByData() throws SQLException {
        return all();
    }
}
