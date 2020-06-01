package net.kubik_13.insta;

import net.kubik_13.insta.model.InstaAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class InstaController {
    String account = "instagram";

    @GetMapping("/insta")
    public String getInfo(Model model) throws IOException {
        InstaAccount acc = new InstaAccount(account);
        model.addAttribute("acc",acc);
        return "insta-info";
    }

    @PostMapping("/insta-change")
    public String changeAcc(InstaAccount account){
        this.account = account.getUserName();
        return "redirect:/insta";
    }
}
