package jp.co.future.uroborosql.springboot.demo.controllers;

import jp.co.future.uroborosql.SqlAgent;
import jp.co.future.uroborosql.springboot.demo.exceptions.UnexpectedException;
import jp.co.future.uroborosql.springboot.demo.models.Specialty;
import jp.co.future.uroborosql.springboot.demo.models.Vet;
import jp.co.future.uroborosql.springboot.demo.models.Vets;
import jp.co.future.uroborosql.utils.CaseFormat;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * VetController
 *
 * @author Kenichi Hoshi
 * @see jp.co.future.uroborosql.springboot.demo.controllers.BaseController
 */
@RestController
@CrossOrigin
public class VetController extends BaseController {

    public VetController(DataSource dataSource) {
        super(dataSource);
    }

    @RequestMapping(value = "/api/vets", method = RequestMethod.GET)
    public List<Map<String, Object>> all() throws SQLException {
        try (SqlAgent agent = createAgent()) {
            return agent.query("vets-all")
                .collect(CaseFormat.CamelCase);
        }
    }

    @RequestMapping(value = {"/vets.json", "/vets.xml"})
    public @ResponseBody
    Vets allByData() throws SQLException {
        try (SqlAgent agent = createAgent()) {
            return agent.required(() -> new Vets(
                agent.query("vets-all")
                    .stream(rs -> new Vet(
                        rs.getInt("ID"),
                        rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME")
                    ))
                    .peek(v -> {
                        try {
                            v.setSpecialties(
                                agent.query("specialties-by-vets")
                                    .param("id", v.getId())
                                    .stream(rs -> new Specialty(
                                        rs.getInt("ID"),
                                        rs.getString("NAME")
                                    ))
                                    .collect(toList())
                            );
                        } catch (SQLException e) {
                            throw new UnexpectedException(e);
                        }
                    }).collect(toList())
            ));
        }
    }
}
