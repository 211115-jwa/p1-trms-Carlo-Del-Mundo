package com.revature.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.EventTypeDAO;
import com.revature.data.GradingFormatDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.utils.DAOFactory;

public class RequestReviewServiceImpl implements RequestReviewService {
	private ReimbursementDAO reimbursementDao = DAOFactory.getReimbursementDAO();
	private StatusDAO statusDao = DAOFactory.getStatusDAO();
	private CommentDAO commentDao = DAOFactory.getCommentDAO();
	private GradingFormatDAO gradingDao = DAOFactory.getGradingFormatDAO();
	private EventTypeDAO eventDao = DAOFactory.getEventTypeDAO();

	@Override
	public Set<Reimbursement> getPendingReimbursements(Employee approver) {
		// TODO Auto-generated method stub

		return reimbursementDao.getPendingByApprover(approver);
	}

	@Override
	public boolean approveRequest(Reimbursement request) {
		// TODO Auto-generated method stub
		int id = 0;
		if(request.getStatus().getStatusId() < 4) {
			id = request.getStatus().getStatusId() + 1;
		} else {
			id = request.getStatus().getStatusId();
		}
		Status stat = statusDao.getById(id);
		request.setStatus(stat);
		return reimbursementDao.update(request);
	}

	@Override
	public boolean rejectRequest(Reimbursement request) {
		// TODO Auto-generated method stub
		
		Status stat = statusDao.getById(0);
		request.setStatus(stat);
		return reimbursementDao.update(request);
	}

	@Override
	public boolean rejectRequest(Reimbursement request, Comment comment) {
		// TODO Auto-generated method stub
		boolean reqUpdated = false;
		Status stat = statusDao.getById(0);
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

	@Override
	public Reimbursement getRequestByID(int id) {
		// TODO Auto-generated method stub
		return reimbursementDao.getById(id);
	}

	@Override
	public GradingFormat getGradingFormatByID(int id) {
		// TODO Auto-generated method stub
		return gradingDao.getById(id);
	}

	@Override
	public EventType getEventTypeByID(int id) {
		// TODO Auto-generated method stub
		return eventDao.getById(id);
	}

	@Override
	public Set<Reimbursement> getAllRequests() {
		// TODO Auto-generated method stub
		return reimbursementDao.getAll();
	}

}
