package org.mrpaulwoods.filtering;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrpaulwoods.filtering.search.SmartColumn;
import org.mrpaulwoods.filtering.search.SearchService;
import org.mrpaulwoods.filtering.search.SmartResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
@Slf4j
public class ContactController {

    private final ContactRepository contactRepository;
    private final SearchService searchService;

    @GetMapping
    ResponseEntity<SmartResult<Contact>> page(@RequestParam(name="filter", required=false) List<String> filters, Pageable pageable) {

        List<SmartColumn> columns = new ArrayList<>();
        columns.add(new SmartColumn("id", "ID", false));
        columns.add(new SmartColumn("firstName", "First Name", true));
        columns.add(new SmartColumn("lastName", "Last Name", true));

        SmartResult<Contact> result = searchService.createSpecification(contactRepository, columns, pageable, filters);

        return ResponseEntity.ok(result);
    }

//    ResponseEntity<Page<Contact>> page(Pageable pageable) {
//
//        ExampleMatcher matcher = ExampleMatcher.matching();
//        matcher = matcher.withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains());
//        matcher= matcher.withIgnoreCase();
//
//        Contact contact = new Contact();
//        contact.setFirstName("k");
//
//        Example<Contact> example = Example.of(contact, matcher);
//
//        return ResponseEntity.ok(contactRepository.findAll(example, pageable));
//    }

    @PostConstruct
    public void init() {
        log.info("inserting db");

        log.info("{}", contactRepository.save(new Contact("Lake", "Kristina")));
        log.info("{}", contactRepository.save(new Contact("Aria", "Lexy")));
        log.info("{}", contactRepository.save(new Contact("Mason", "Lamont")));
        log.info("{}", contactRepository.save(new Contact("Odin", "Dora")));
        log.info("{}", contactRepository.save(new Contact("Kendra", "Rosalyn")));

        log.info("dumping db");
        contactRepository.findAll().forEach(c -> log.info("{}", c));
    }
}
