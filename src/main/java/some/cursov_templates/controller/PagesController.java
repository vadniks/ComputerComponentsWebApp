package some.cursov_templates.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import some.cursov_templates.entity.PcComponent;
import some.cursov_templates.service.ComponentsService;

import javax.servlet.http.HttpServletRequest;

import static some.cursov_templates.Constants.*;

@RequiredArgsConstructor
@Controller
public class PagesController {
    @ImplicitAutowire
    private final ComponentsService componentsService;

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
        return isAuthenticated(request) ? PAGE_LOGIN : REDIRECT_TO_INDEX;
    }

    @GetMapping(ENDPOINT_REGISTER)
    public String register(HttpServletRequest request) {
        return !isAuthenticated(request) ? PAGE_REGISTER : REDIRECT_TO_INDEX;
    }

    @GetMapping(ENDPOINT_ADMIN)
    public String admin() {
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
