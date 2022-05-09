package some.cursov_templates.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import some.cursov_templates.entity.PcComponent;
import some.cursov_templates.entity.User;
import some.cursov_templates.service.ComponentsService;
import some.cursov_templates.service.UsersService;

import javax.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.util.List;

import static some.cursov_templates.Constants.*;

@RequiredArgsConstructor
@Controller
public class RestController {
    @ImplicitAutowire
    private final ComponentsService componentsService;
    @ImplicitAutowire
    private final UsersService usersService;

    @ResponseBody
    @GetMapping(value = GET_COMPONENT, produces = MediaType.APPLICATION_JSON_VALUE)
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
    @GetMapping(value = GET_USER, produces = MediaType.APPLICATION_JSON_VALUE)
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
    @PostMapping(POST_INSERT_OR_UPDATE)
    public EmptyResponse insertOrUpdate(
        @RequestBody(required = false) @Nullable PcComponent component,
        @RequestBody(required = false) @Nullable User user
    ) {
        if (component == null && user == null) throw new IllegalArgumentException();
        else if (component != null) componentsService.saveComponent(component);
        else usersService.saveUser(user);
        return STATUS_OK;
    }

    @PreAuthorize(HAS_ROLE_ADMIN)
    @PostMapping(GET_SELECT)
    public List<? extends Serializable> select(
        @RequestParam boolean entity,
        @RequestParam String byWhich,
        @RequestParam String selection
    ) {
        return entity ?
            componentsService.selectComponents(byWhich, selection) :
            usersService.selectUsers(byWhich, selection);
    }
}
