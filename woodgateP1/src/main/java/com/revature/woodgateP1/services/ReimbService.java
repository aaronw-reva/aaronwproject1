package com.revature.woodgateP1.services;

import com.revature.woodgateP1.daos.ReimbDAO;
import com.revature.woodgateP1.daos.UserDAO;
import com.revature.woodgateP1.models.DTOs.IncomingReimbDTO;
import com.revature.woodgateP1.models.DTOs.IncomingReimbDescDTO;
import com.revature.woodgateP1.models.DTOs.IncomingStatusUpdateDTO;
import com.revature.woodgateP1.models.Reimb;
import com.revature.woodgateP1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReimbService {
    private ReimbDAO rDAO;
    private UserDAO uDAO;

    @Autowired
    public ReimbService(ReimbDAO rDAO, UserDAO uDAO) {
        this.rDAO = rDAO;
        this.uDAO = uDAO;
    }

    public Reimb addReimb(IncomingReimbDTO reimbDTO){
        Reimb newReimb = new Reimb(0, reimbDTO.getDescription(), reimbDTO.getAmount(), "PENDING", null);
        Optional<User> u = uDAO.findById(reimbDTO.getUserId());

        if(u.isEmpty()){
            throw new IllegalArgumentException("No user found with id: " + reimbDTO.getUserId());
        } else {
            //set the user object in the new reimb
            newReimb.setUser(u.get()); //.get() is what extracts the value from the Optional

            //send the reimb to the DAO
            return rDAO.save(newReimb);
        }
    }

    public Reimb updateStatus(IncomingStatusUpdateDTO statusDTO){
        Reimb reimbToUpdate = rDAO.getReferenceById(statusDTO.getReimbId());
        reimbToUpdate.setStatus(statusDTO.getStatus());
        return rDAO.save(reimbToUpdate);
    }

    public Reimb updateDescription(IncomingReimbDescDTO descDTO){
        Reimb reimbToUpdate = rDAO.getReferenceById(descDTO.getReimbId());
        reimbToUpdate.setDescription(descDTO.getDescription());
        return rDAO.save(reimbToUpdate);
    }

    public List<Reimb> getAllReimbs() {
        return rDAO.findAll();
    }

    public List<Reimb> getReimbsByUserId(int userId) {
        return rDAO.findByUserUserId(userId);
    }

    public List<Reimb> getPendingReimbs() {
        return rDAO.findByStatus("PENDING");
    }

    public List<Reimb> getPendingReimbsByUserId(int userId, String status) {
        return rDAO.findByUserUserIdAndStatus(userId, status);
    }

    public void deleteReimbById(int reimbId){
        rDAO.deleteById(reimbId);
    }

    public long getPendingCount(){
        String status = "PENDING";
        return rDAO.countByStatus(status);
    }

    public long calcApprovedByUserId(int userId){
        return rDAO.calcSumOfApprovedById(userId);
    }
}
