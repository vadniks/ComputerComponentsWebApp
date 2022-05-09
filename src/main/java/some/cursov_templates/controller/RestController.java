package some.cursov_templates.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import some.cursov_templates.entity.PcComponent;
import some.cursov_templates.entity.User;
import some.cursov_templates.service.ComponentsService;
import some.cursov_templates.service.UsersService;

import javax.servlet.http.HttpServletRequest;

import static some.cursov_templates.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@Controller
public class RestController {
    @ImplicitAutowire
    private final ComponentsService componentsService;
    @ImplicitAutowire
    private final UsersService usersService;

    @ResponseBody
    @GetMapping(value = GET_COMPONENT, produces = APPLICATION_JSON_VALUE)
    public StringPairMap component(@RequestParam String id) {
        return componentsService.getComponent(Integer.parseInt(id));
    }

    @PostMapping(POST_SELECT)
    public EmptyResponse select(HttpServletRequest request, @RequestParam String id) {
        componentsService.setSelection(request, id);
        return STATUS_OK;
    }

    @PostMapping(POST_CLEAR)
    public EmptyResponse clear(HttpServletRequest request) {
        componentsService.setSelection(request, null);
        return STATUS_OK;
    }

    @PreAuthorize(HAS_ROLE_ADMIN)
    @GetMapping(value = GET_USER, produces = APPLICATION_JSON_VALUE)
    public User user(@RequestParam String id) {
        return usersService.getUser(toInt(id));
    }

    @PreAuthorize(HAS_ROLE_ADMIN)
    @PostMapping(POST_REMOVE)
    public EmptyResponse remove(@RequestParam boolean entity, @RequestParam String id) {
        val _id = toInt(id);
        if (entity) componentsService.removeComponent(_id);
        else usersService.removeUser(_id);
        return STATUS_OK;
    }

    @PreAuthorize(HAS_ROLE_ADMIN)
    @PostMapping(value = POST_INSERT_OR_UPDATE_COMPONENT, consumes = APPLICATION_JSON_VALUE)
    public EmptyResponse insertOrUpdate(@RequestBody(required = false) PcComponent component) {
        componentsService.saveComponent(component);
        return STATUS_OK;
    }

    @PreAuthorize(HAS_ROLE_ADMIN)
    @PostMapping(value = POST_INSERT_OR_UPDATE_USER, consumes = APPLICATION_JSON_VALUE)
    public EmptyResponse insertOrUpdate(@RequestBody(required = false) User user) {
        usersService.saveUser(user);
        return STATUS_OK;
    }
}
