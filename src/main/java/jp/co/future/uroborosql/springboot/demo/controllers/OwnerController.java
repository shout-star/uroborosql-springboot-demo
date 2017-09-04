package jp.co.future.uroborosql.springboot.demo.controllers;

import jp.co.future.uroborosql.SqlAgent;
import jp.co.future.uroborosql.springboot.demo.exceptions.NotFoundException;
import jp.co.future.uroborosql.springboot.demo.models.Owner;
import jp.co.future.uroborosql.utils.CaseFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * OwnerController
 *
 * @author Kenichi Hoshi
 * @see jp.co.future.uroborosql.springboot.demo.controllers.BaseController
 */
@RestController
@CrossOrigin
@RequestMapping("/api/owners")
public class OwnerController extends BaseController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Map<String, Object>> find(@RequestParam(required = false) String lastName) throws SQLException {
        try (SqlAgent agent = createAgent()) {
            return agent.query("owners-find")
                .param("lastName", lastName)
                .collect(CaseFormat.CAMEL_CASE);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Map<String, Object> get(@PathVariable("id") int id) throws SQLException {
        try (SqlAgent agent = createAgent()) {
            Map<String, Object> owner = new HashMap<>();

            agent.required(() -> {
                owner.putAll(
                    agent.query("owners-get")
                        .param("id", id)
                        .collect(CaseFormat.CAMEL_CASE)
                        .stream()
                        .findFirst()
                        .orElseThrow(NotFoundException::new));
                List<Map<String, Object>> pets = agent.query("pets-find")
                    .param("ownerId", id)
                    .collect(CaseFormat.CAMEL_CASE);
                if (!pets.isEmpty()) {
                    List<Map<String, Object>> visits = agent.query("visits-find")
                        .param("petId", pets.stream().mapToInt(p -> (int) p.get("id")).toArray())
                        .collect(CaseFormat.CAMEL_CASE);

                    pets.forEach(p -> {
                        List<Map<String, Object>> visitsByPet = visits.stream()
                            .filter(v -> (int) v.get("petId") == (int) p.get("id"))
                            .collect(Collectors.toList());
                        p.put("visits", visitsByPet);
                    });
                }
                owner.put("pets", pets);
            });


            return owner;
        }
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Map<String, Object> create(@Validated @RequestBody Owner owner) throws SQLException {
        return handleCreate(owner);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public void update(@PathVariable("id") int id, @Validated @RequestBody Owner owner) throws SQLException {
        handleUpdate(id, owner);
    }

}
