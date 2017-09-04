package jp.co.future.uroborosql.springboot.demo.controllers;

import jp.co.future.uroborosql.springboot.demo.models.Pet;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * PetController
 *
 * @author Kenichi Hoshi
 * @see jp.co.future.uroborosql.springboot.demo.controllers.BaseController
 */
@RestController
@CrossOrigin
@RequestMapping("/api/pets")
public class PetController extends BaseController {

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Map<String, Object> create(@Validated @RequestBody Pet pet) {
        return handleCreate(pet);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public void update(@PathVariable("id") int id, @Validated @RequestBody Pet pet) {
        handleUpdate(id, pet);
    }
}
