package net.haidarali.Receptacle.controllers;

import net.haidarali.Receptacle.services.PostIngestionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class IngestController {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private PostIngestionService postIngestionService;

    public IngestController(PostIngestionService postIngestionService) {
        this.postIngestionService = postIngestionService;
    }

    @GetMapping("/ingest")
    public String ingestView() {
        return "ingest_view";
    }

    @PostMapping("/ingest")
    public ModelAndView ingestArchive(@RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ingest_feedback");
        try {
            postIngestionService.ingestPosts(file);
            modelAndView.addObject("message", "Archive ingested successfully");
        } catch (Exception e) {
            modelAndView.addObject("message", "Archive ingestion failed");
            logger.log(Level.SEVERE, e.toString());
        }
        return modelAndView;
    }
}
