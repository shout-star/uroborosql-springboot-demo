package jp.co.future.uroborosql.springboot.demo.controllers;

import jp.co.future.uroborosql.springboot.demo.models.Visit;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * VisitController
 *
 * @author Kenichi Hoshi
 * @see jp.co.future.uroborosql.springboot.demo.controllers.BaseController
 */
@RestController
@CrossOrigin
@RequestMapping("/api/visits")
public class VisitController extends BaseController {

    public VisitController(DataSource dataSource) {
        super(dataSource);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Map<String, Object> create(@Validated @RequestBody Visit visit) throws SQLException {
        return handleCreate(visit);
    }

}
