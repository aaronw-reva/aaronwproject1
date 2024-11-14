package com.revature.woodgateP1.controllers;

import com.revature.woodgateP1.aspects.AdminOnly;
import com.revature.woodgateP1.models.DTOs.IncomingReimbDTO;
import com.revature.woodgateP1.models.DTOs.IncomingReimbDescDTO;
import com.revature.woodgateP1.models.DTOs.IncomingStatusUpdateDTO;
import com.revature.woodgateP1.models.Reimb;
import com.revature.woodgateP1.services.ReimbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reimbs")
@CrossOrigin
public class ReimbController {
    private ReimbService reimbService;

    @Autowired
    public ReimbController(ReimbService reimbService) {
        this.reimbService = reimbService;
    }

    @PostMapping
    public ResponseEntity<Reimb> insertReimb(@RequestBody IncomingReimbDTO reimbDTO) {
        Reimb r = reimbService.addReimb(reimbDTO);
        return ResponseEntity.status(201).body(r);
    }

    @PatchMapping("/desc")
    public ResponseEntity<Reimb> updateReimbDescription(@RequestBody IncomingReimbDescDTO descDTO) {
        Reimb r = reimbService.updateDescription(descDTO);
        return ResponseEntity.status(200).body(r);
    }

    @AdminOnly
    @PatchMapping("/status")
    public ResponseEntity<Reimb> updateReimbStatus(@RequestBody IncomingStatusUpdateDTO statusDTO) {
        Reimb r = reimbService.updateStatus(statusDTO);
        return ResponseEntity.status(200).body(r);
    }

    //A method that gets all Pets from the DB
    @GetMapping //GET requests to /pets will come here
    public ResponseEntity<List<Reimb>> getAllReimbs(){
        return ResponseEntity.ok(reimbService.getAllReimbs());
    }

    @GetMapping("/user/{userId}") //GET requests to /pets/user/{userId} will come here
    public ResponseEntity<List<Reimb>> getReimbsByUserId(@PathVariable int userId){
        return ResponseEntity.ok(reimbService.getReimbsByUserId(userId));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Reimb>> getPendingReimbs(){
        return ResponseEntity.ok(reimbService.getPendingReimbs());
    }

    @GetMapping("/user/{userId}/pending")
    public ResponseEntity<List<Reimb>> getPendingReimbsByUserId(@PathVariable int userId){
        return ResponseEntity.ok(reimbService.getPendingReimbsByUserId(userId, "PENDING"));
    }

    @GetMapping("/total/{userId}")
    public ResponseEntity<Long> getApprovedSum(@PathVariable int userId){
        return ResponseEntity.ok(reimbService.calcApprovedByUserId(userId));
    }

    @AdminOnly
    @GetMapping("/pendingcount")
    public ResponseEntity<Long> getPendingCount(){
        return ResponseEntity.ok(reimbService.getPendingCount());
    }

    //delete pet by id
    @DeleteMapping("/{reimbId}")
    public ResponseEntity<String> deletePetById(@PathVariable int reimbId){
        reimbService.deleteReimbById(reimbId);
        return ResponseEntity.ok("reimb with id " + reimbId + " has been deleted");
    }

    //Exception Handler (stole this from the UserController)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        //Return a 400 (BAD REQUEST) status code with the exception message
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
