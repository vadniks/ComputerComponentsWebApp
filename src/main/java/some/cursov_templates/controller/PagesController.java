package some.cursov_templates.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import some.cursov_templates.entity.PcComponent;
import some.cursov_templates.service.ComponentsService;
import some.cursov_templates.service.UsersService;

import javax.servlet.http.HttpServletRequest;

import static some.cursov_templates.Constants.*;

@RequiredArgsConstructor
@Controller
public class PagesController {
    @ImplicitAutowire
    private final ComponentsService componentsService;
    @ImplicitAutowire
    private final UsersService usersService;

    @SneakyThrows
    @GetMapping(ENDPOINT_INDEX)
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute(ATTRIBUTE_TOTAL, componentsService.getTotalCost(request));
        model.addAttribute(ATTRIBUTE_ITEMS, componentsService.getOverviewItems(request));
        return PAGE_INDEX;
    }

    @GetMapping(ENDPOINT_BROWSE)
    public String browse(Model model, @RequestParam String type) {
        model.addAttribute(ATTRIBUTE_TYPE, PcComponent.Type.valueOf(type).REAL_NAME);
        model.addAttribute(ATTRIBUTE_ITEMS, componentsService.getComponentsByType(type));
        return PAGE_BROWSE;
    }

    @GetMapping(ENDPOINT_LOGIN)
    public String login(HttpServletRequest request) {
        return !isAuthenticated(request) ? PAGE_LOGIN : REDIRECT_TO_INDEX;
    }

    @GetMapping(ENDPOINT_REGISTER)
    public String register(HttpServletRequest request) {
        return !isAuthenticated(request) ? PAGE_REGISTER : REDIRECT_TO_ERROR;
    }

    @GetMapping(ENDPOINT_ADMIN)
    public String admin(Model model) {
        if (!model.containsAttribute(ATTRIBUTE_COMPONENTS))
            model.addAttribute(ATTRIBUTE_COMPONENTS, componentsService.getAllComponents());

        if (!model.containsAttribute(ATTRIBUTE_USERS))
            model.addAttribute(ATTRIBUTE_USERS, usersService.getAllUsers());

        return PAGE_ADMIN;
    }

    @GetMapping(ENDPOINT_ABOUT)
    public String about() {
        return PAGE_ABOUT;
    }

    @GetMapping(ENDPOINT_ERROR)
    public String error() {
        return PAGE_ERROR;
    }

    @PreAuthorize(HAS_ROLE_ADMIN)
    @GetMapping(value = GET_SELECT)
    public String select(
        Model model,
        @RequestParam boolean entity,
        @RequestParam String byWhich,
        @RequestParam String selection
    ) {
        if (entity)
            model.addAttribute(ATTRIBUTE_COMPONENTS,
                componentsService.selectComponents(byWhich, selection));
        else
            model.addAttribute(ATTRIBUTE_USERS,
                usersService.selectUsers(byWhich, selection));
        return REDIRECT_TO_ADMIN;
    }
}
