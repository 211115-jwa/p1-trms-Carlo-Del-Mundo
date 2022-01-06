package com.revature.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.utils.DAOFactory;

public class RequestReviewServiceImpl implements RequestReviewService {
	private ReimbursementDAO reimbursementDao = DAOFactory.getReimbursementDAO();
	private StatusDAO statusDao = DAOFactory.getStatusDAO();
	private CommentDAO commentDao = DAOFactory.getCommentDAO();

	@Override
	public Set<Reimbursement> getPendingReimbursements(Employee approver) {
		// TODO Auto-generated method stub
		Status stat = statusDao.getById(1);
		Set<Reimbursement> requests = reimbursementDao.getByStatus(stat);
		
//		Set<Reimbursement> approverRequests = new HashSet<>();
//		requests.forEach(request -> {
//			if(request.getRequestor().getSupervisor().getEmpId() == approver.getEmpId()) {
//				approverRequests.add(request);
//			}
//		});
//		
//		return approverRequests;
		return requests;
	}

	@Override
	public boolean approveRequest(Reimbursement request) {
		// TODO Auto-generated method stub
		Status stat = statusDao.getById(2);
		request.setStatus(stat);
		return reimbursementDao.update(request);
	}

	@Override
	public boolean rejectRequest(Reimbursement request) {
		// TODO Auto-generated method stub
		Status stat = statusDao.getById(3);
		request.setStatus(stat);
		return reimbursementDao.update(request);
	}

	@Override
	public boolean rejectRequest(Reimbursement request, Comment comment) {
		// TODO Auto-generated method stub
		boolean reqUpdated = false;
		Status stat = statusDao.getById(3);
		request.setStatus(stat);
		comment.setSentAt(LocalDateTime.now());
		comment.setRequest(request);
		int generatedId = commentDao.create(comment);
		if(generatedId>0) {
			reqUpdated = reimbursementDao.update(request);
		} else {
			reqUpdated = false;
		}
		
		return reqUpdated;
	}

}
