package dao;

import entity.Policy;
import exception.PolicyNotFoundException;

import java.util.List;

public interface IPolicyService {
    boolean createPolicy(Policy policy);
    Policy getPolicy(int policyId) throws PolicyNotFoundException;
    List<Policy> getAllPolicies();
    boolean updatePolicy(Policy policy);
	boolean deletePolicy(int policyId);
   
    
}
