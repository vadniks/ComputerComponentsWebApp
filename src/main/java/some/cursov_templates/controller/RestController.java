package some.cursov_templates.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import some.cursov_templates.entity.User;
import some.cursov_templates.service.ComponentsService;
import some.cursov_templates.service.UsersService;

import javax.servlet.http.HttpServletRequest;

import static some.cursov_templates.Constants.*;
import static some.cursov_templates.Constants.STATUS_OK;

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
    public EmptyResponse select(
        HttpServletRequest request,
        @RequestParam String id
    ) {
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
    @PostMapping(POST_REMOVE_COMPONENT)
    public EmptyResponse removeComponent(@RequestParam String id) {
        componentsService.removeComponent(toInt(id));
        return STATUS_OK;
    }

    @PreAuthorize(HAS_ROLE_ADMIN)
    @PostMapping(POST_REMOVE_USER)
    public EmptyResponse removeUser(@RequestParam String id) {
        usersService.removeUser(toInt(id));
        return STATUS_OK;
    }


}
