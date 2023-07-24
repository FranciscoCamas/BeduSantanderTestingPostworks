package org.bedu.testing.controllers;

import org.bedu.testing.services.imp.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.bedu.testing.models.InterviewerDTO;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * @author Francisco Javier Camas Tec  francisco_Camas@hotmail.com
 */
@Validated
@RestController
@RequestMapping("/interviewer")
public class InterviewerController {

    @Autowired
    private final InterviewerService interviewerService;

    public InterviewerController(InterviewerService interviewerService) {
        this.interviewerService = interviewerService;
    }

    /**
     * Get all clients
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<InterviewerDTO>> getInterviewers(){

        return ResponseEntity.ok(this.interviewerService.findAllInterviewers ());
    }

    /**
     * Get the specified client
     *
     * @param clientId
     * @return
     */
    @GetMapping("/{interviewerId}")
    public ResponseEntity<InterviewerDTO> getinterviewer(@PathVariable Long interviewerId){

        Optional<InterviewerDTO> clienteDb = this.interviewerService.findInterviewerById (interviewerId);

        if (clienteDb.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The request client does not exist.");

        return ResponseEntity.ok(clienteDb.get());
    }


    /**
     * Create a client
     *
     * @param interviewerDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<InterviewerDTO> createClient(@Validated @RequestBody InterviewerDTO interviewerDTO){
        return ResponseEntity.created(URI.create(this.interviewerService.saveInterviewer (interviewerDTO).toString().replace(" " ,"%20") )).build();
    }

    /**
     * Update a client
     *
     * @param interviewerId
     * @param interviewer
     * @return
     */
    @PutMapping("/{interviewerId}")
    public ResponseEntity<Void> updateClient(@PathVariable Long interviewerId,@Validated  @RequestBody InterviewerDTO interviewer){

        this.interviewerService.updateInterviewer (interviewerId,interviewer);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Delete a client
     *
     * @param interviewerId
     * @return
     */
    @DeleteMapping("/{interviewerId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long interviewerId){
        Optional<InterviewerDTO> interviewerDb = this.interviewerService.findInterviewerById (interviewerId);

        if (interviewerDb.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The interviewer that you want delete does not exist.");

        this.interviewerService.delete(interviewerId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
