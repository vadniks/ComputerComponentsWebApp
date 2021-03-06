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
        model.addAttribute(ATTRIBUTE_HAS_ORDERED, usersService.hasOrdered(request));
        return PAGE_INDEX;
    }

    @GetMapping(ENDPOINT_BROWSE)
    public String browse(Model model, @RequestParam String type) {
        model.addAttribute(ATTRIBUTE_TYPE, PcComponent.Type.valueOf(type).REAL_NAME);
        model.addAttribute(ATTRIBUTE_ITEMS, componentsService.getComponentsByType(type));
        return PAGE_BROWSE;
    }

    @PreAuthorize(IS_ANONYMOUS)
    @GetMapping(ENDPOINT_LOGIN)
    public String login(HttpServletRequest request) {
        return isNotAuthenticated(request) ? PAGE_LOGIN : REDIRECT_TO_INDEX;
    }

    @PreAuthorize(IS_ANONYMOUS)
    @GetMapping(ENDPOINT_REGISTER)
    public String register(HttpServletRequest request) {
        return isNotAuthenticated(request) ? PAGE_REGISTER : REDIRECT_TO_ERROR;
    }

    @PreAuthorize(HAS_ROLE_ADMIN)
    @GetMapping(ENDPOINT_ADMIN)
    public String admin(
        Model model,
        @RequestParam(required = false) Boolean entity,
        @RequestParam(required = false) String byWhich,
        @RequestParam(required = false) String selection
    ) {
        if (entity == null) {
            model.addAttribute(ATTRIBUTE_COMPONENTS, componentsService.getAllComponents());
            model.addAttribute(ATTRIBUTE_USERS, usersService.getAllUsers());
            return PAGE_ADMIN;
        }

        if (entity)
            model.addAttribute(ATTRIBUTE_COMPONENTS,
                componentsService.selectComponents(byWhich, selection));
        else
            model.addAttribute(ATTRIBUTE_USERS,
                usersService.selectUsers(byWhich, selection));
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
}
