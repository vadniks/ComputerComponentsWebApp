package some.cursov_templates.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import some.cursov_templates.entity.PcComponent;
import some.cursov_templates.service.ComponentsService;

import javax.servlet.http.HttpServletRequest;

import static some.cursov_templates.Constants.*;
import static some.cursov_templates.entity.User.Role.ADMIN;

@RequiredArgsConstructor
@Controller
public class PagesController {
    @ImplicitAutowire
    private final ComponentsService componentsService;

    @SneakyThrows
    @GetMapping(ENDPOINT_INDEX)
    public String index(Model model, HttpServletRequest request) {
        val b = request;

        for (val i : b.getClass().getSuperclass().getDeclaredFields()) {
            debug("rfvfv", i.getName(), i.getType());
            if (i.getName().equals("rolePrefix")) {
                i.setAccessible(true);
                debug("fbvgftvbfvbfr", i.get(b));
            }
        }

        val a = SecurityContextHolder.getContext().getAuthentication();
        debug("fvbgfrgv", a.getCredentials(), a.getAuthorities(), a.getPrincipal());
        debug("fdhgkl", request.getClass(), new SimpleGrantedAuthority(ADMIN.mkRole()).getAuthority(), request.getUserPrincipal(), request.isUserInRole(ADMIN.mkRole()));
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
    public String login() {
        return PAGE_LOGIN;
    }

    @GetMapping(ENDPOINT_REGISTER)
    public String register() {
        return PAGE_REGISTER;
    }

    @GetMapping(ENDPOINT_ADMIN)
    public String admin() {
        return PAGE_ADMIN;
    }

    @GetMapping(ENDPOINT_ABOUT)
    public String about() {
        return PAGE_ABOUT;
    }
}
