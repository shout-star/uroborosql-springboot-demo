package jp.co.future.uroborosql.springboot.demo.controllers;

import jp.co.future.uroborosql.SqlAgent;
import jp.co.future.uroborosql.springboot.demo.models.Pet;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/pets")
public class PetController extends BaseController {

    public PetController(DataSource dataSource) {
        super(dataSource);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Map<String, Object> create(@Validated @RequestBody Pet pet) throws SQLException {
        try (SqlAgent agent = createAgent()) {
            Map<String, Object> result = new HashMap<>();

            agent.required(() -> {
                agent.insert(pet);
                result.putAll(generatedKeys(agent));
            });
            return result;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public void update(@PathVariable("id") int id, @Validated @RequestBody Pet pet) throws SQLException {
        try (SqlAgent agent = createAgent()) {
            agent.required(() -> {
                pet.setId(id);
                agent.update(pet);
            });
        }
    }
}
