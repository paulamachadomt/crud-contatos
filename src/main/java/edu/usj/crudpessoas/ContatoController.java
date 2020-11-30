package edu.usj.crudpessoas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ContatoController {
    
    @Autowired
    ContatoRepository contatoRepository;

    @GetMapping(value="/")
    public ModelAndView readListaContatos() {
        List<Contato> listaContatos = contatoRepository.findAll();
        
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("listaContatos", listaContatos);
        return modelAndView;
    }

    @GetMapping(value="/detalhes/{id}")
    public ModelAndView readContatoById(@PathVariable Long id) {

        //ler do banco
        Contato contato = contatoRepository.findById(id).get();       
        
        //acrescentar na modelAndView
        ModelAndView modelAndView = new ModelAndView("detalhes");
        modelAndView.addObject("contato", contato);
        return modelAndView;

    }

    @GetMapping(value="/cadastro")
    public ModelAndView createContato() {
        Contato contato = new Contato();
        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("contato", contato);
        return modelAndView;
    }


    @PostMapping(value="/adicionar")
    public String adicionarContato(Contato contato) {
        
        contatoRepository.save(contato);
        return "redirect:/";
        
        // List<Contato> listaContatos = contatoRepository.findAll();

        // ModelAndView modelAndView = new ModelAndView("index");
        // modelAndView.addObject("listaContatos", listaContatos);
        // return modelAndView;       
    }
    

    @GetMapping(value="/deletar/{id}")
    public String deleteContatoById(@PathVariable Long id) {
        contatoRepository.deleteById(id);             
        return "redirect:/";  
    }


    @GetMapping(value="/editar/{id}")
    public ModelAndView updateContatoById(@PathVariable Long id) {
        Contato contato = contatoRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("contato", contato);
        return modelAndView;
    }


}
 
    
