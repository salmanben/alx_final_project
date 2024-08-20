package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.DTO.AwsS3SettingsDto;
import com.example.demo.DTO.EmailSettingsDto;
import com.example.demo.DTO.GeneralSettingsDto;
import com.example.demo.DTO.SmsSettingsDto;
import com.example.demo.DTO.StripeSettingsDto;
import com.example.demo.service.AwsS3SettingsService;
import com.example.demo.service.EmailSettingsService;
import com.example.demo.service.GeneralSettingsService;
import com.example.demo.service.SmsSettingsService;
import com.example.demo.service.StripeSettingsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class SettingsController {

    private final GeneralSettingsService generalSettingsService;
    private final EmailSettingsService emailSettingsService;
    private final StripeSettingsService stripeSettingsService;
    private final SmsSettingsService smsSettingsService;
    private final AwsS3SettingsService awsS3SettingsService;

    public SettingsController(GeneralSettingsService generalSettingsService, EmailSettingsService emailSettingsService,
             StripeSettingsService stripeSettingsService, SmsSettingsService smsSettingsService, AwsS3SettingsService awsS3SettingsService) {
        this.generalSettingsService = generalSettingsService;
        this.emailSettingsService = emailSettingsService;
        this.stripeSettingsService = stripeSettingsService;
        this.smsSettingsService = smsSettingsService;
        this.awsS3SettingsService = awsS3SettingsService;
    }

    @GetMapping("/admin/settings")
    public String index(Model model) {

        GeneralSettingsDto generalSettings = generalSettingsService.getGeneralSettings();
        EmailSettingsDto emailSettings = emailSettingsService.getEmailSettings();
        StripeSettingsDto stripeSettings = stripeSettingsService.getStripeSettings();
        SmsSettingsDto smsSettings = smsSettingsService.getSmsSettings();
        AwsS3SettingsDto awsS3Settings = awsS3SettingsService.getAwsS3Settings();
        


        model.addAttribute("generalSettings", generalSettings);
        model.addAttribute("emailSettings", emailSettings);
        model.addAttribute("stripeSettings", stripeSettings);
        model.addAttribute("smsSettings", smsSettings);
        model.addAttribute("awsS3Settings", awsS3Settings);


        return "admin/settings/index";
    }

    @PostMapping("/admin/settings/general-settings/update")
    public String updateGeneralSettings(@Valid @ModelAttribute("generalSettings") GeneralSettingsDto generalSettingsDto,
            BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        session.setAttribute("settings", "general");
        if (bindingResult.hasErrors()) {
            EmailSettingsDto emailSettings = emailSettingsService.getEmailSettings();
            StripeSettingsDto stripeSettings = stripeSettingsService.getStripeSettings();
            SmsSettingsDto smsSettings = smsSettingsService.getSmsSettings();
            AwsS3SettingsDto awsS3Settings = awsS3SettingsService.getAwsS3Settings();

            model.addAttribute("emailSettings", emailSettings);
            model.addAttribute("stripeSettings", stripeSettings);
            model.addAttribute("smsSettings", smsSettings);
            model.addAttribute("awsS3Settings", awsS3Settings);

            return "admin/settings/index";
        }
        generalSettingsService.updateGeneralSettings(generalSettingsDto);
        redirectAttributes.addFlashAttribute("success", "General settings updated successfully");

        return "redirect:/admin/settings";
    }

    @PostMapping("/admin/settings/email/update")
    public String updateEmailSettings(@Valid @ModelAttribute("emailSettings") EmailSettingsDto emailSettingsDto,
            BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        session.setAttribute("settings", "email");
        if (bindingResult.hasErrors()) {
            GeneralSettingsDto generalSettings = generalSettingsService.getGeneralSettings();
            StripeSettingsDto stripeSettings = stripeSettingsService.getStripeSettings();
            SmsSettingsDto smsSettings = smsSettingsService.getSmsSettings();
            AwsS3SettingsDto awsS3Settings = awsS3SettingsService.getAwsS3Settings();


            model.addAttribute("generalSettings", generalSettings);
            model.addAttribute("stripeSettings", stripeSettings);
            model.addAttribute("smsSettings", smsSettings);
            model.addAttribute("awsS3Settings", awsS3Settings);

            return "admin/settings/index";
        }
        emailSettingsService.updateEmailSettings(emailSettingsDto);
        redirectAttributes.addFlashAttribute("success", "Email settings updated successfully");
        return "redirect:/admin/settings";
    }

    @PostMapping("/admin/settings/stripe/update")
    public String updateStripeSettings(@Valid @ModelAttribute("stripeSettings") StripeSettingsDto stripeSettingsDto,
            BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        session.setAttribute("settings", "stripe");

        if (bindingResult.hasErrors()) {
            GeneralSettingsDto generalSettings = generalSettingsService.getGeneralSettings();
            EmailSettingsDto emailSettings = emailSettingsService.getEmailSettings();
            SmsSettingsDto smsSettings = smsSettingsService.getSmsSettings();
            AwsS3SettingsDto awsS3Settings = awsS3SettingsService.getAwsS3Settings();


            model.addAttribute("generalSettings", generalSettings);
            model.addAttribute("emailSettings", emailSettings);
            model.addAttribute("smsSettings", smsSettings);
            model.addAttribute("awsS3Settings", awsS3Settings);

            return "admin/settings/index";
        }
        stripeSettingsService.updateStripeSettings(stripeSettingsDto);
        redirectAttributes.addFlashAttribute("success", "Stripe settings updated successfully");
        return "redirect:/admin/settings";
    }

    @PostMapping("/admin/settings/sms/update")
    public String updateSmsSettings(@Valid @ModelAttribute("smsSettings") SmsSettingsDto smsSettingsDto,
            BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        session.setAttribute("settings", "sms");

        if (bindingResult.hasErrors()) {
            GeneralSettingsDto generalSettings = generalSettingsService.getGeneralSettings();
            EmailSettingsDto emailSettings = emailSettingsService.getEmailSettings();
            StripeSettingsDto stripeSettings = stripeSettingsService.getStripeSettings();
            AwsS3SettingsDto awsS3Settings = awsS3SettingsService.getAwsS3Settings();


            model.addAttribute("generalSettings", generalSettings);
            model.addAttribute("emailSettings", emailSettings);
            model.addAttribute("stripeSettings", stripeSettings);
            model.addAttribute("awsS3Settings", awsS3Settings);
            return "admin/settings/index";
        }
        smsSettingsService.updateSmsSettings(smsSettingsDto);
        redirectAttributes.addFlashAttribute("success", "SMS settings updated successfully");
        return "redirect:/admin/settings";
    }

    @PostMapping("/admin/settings/awsS3/update")
    public String updateAwsS3Settings(@Valid @ModelAttribute("awsS3Settings") AwsS3SettingsDto awsS3SettingsDto,
            BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        session.setAttribute("settings", "awsS3");

        if (bindingResult.hasErrors()) {
            GeneralSettingsDto generalSettings = generalSettingsService.getGeneralSettings();
            EmailSettingsDto emailSettings = emailSettingsService.getEmailSettings();
            StripeSettingsDto stripeSettings = stripeSettingsService.getStripeSettings();
            SmsSettingsDto smsSettings = smsSettingsService.getSmsSettings();

            model.addAttribute("generalSettings", generalSettings);
            model.addAttribute("emailSettings", emailSettings);
            model.addAttribute("stripeSettings", stripeSettings);
            model.addAttribute("smsSettings", smsSettings);
            return "admin/settings/index";
        }
        awsS3SettingsService.updateAwsS3Settings(awsS3SettingsDto);
        redirectAttributes.addFlashAttribute("success", "AWS S3 settings updated successfully");
        return "redirect:/admin/settings";
    }

}
