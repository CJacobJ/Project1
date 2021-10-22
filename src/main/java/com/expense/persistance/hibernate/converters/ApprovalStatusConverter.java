package com.expense.persistance.hibernate.converters;

import com.expense.exceptions.ApprovalStatusIndexOutOfBounds;
import com.expense.model.requests.ApprovalStatus;
import com.expense.model.requests.ApprovedStatus;
import com.expense.model.requests.DeniedStatus;
import com.expense.model.requests.PendingStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ApprovalStatusConverter implements AttributeConverter<ApprovalStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ApprovalStatus approvalStatus) {
        return Integer.valueOf(approvalStatus.toInt());
    }

    @Override
    public ApprovalStatus convertToEntityAttribute(Integer integer) {
        ApprovalStatus newApprovalStatus;
        int i = integer.intValue();
        switch(i) {
            case 0:
                newApprovalStatus = new PendingStatus();
                break;
            case 1:
                newApprovalStatus = new ApprovedStatus();
                break;
            case 2:
                newApprovalStatus = new DeniedStatus();
                break;
            default:
                throw new ApprovalStatusIndexOutOfBounds("newApprovalID is not a legal value! newApprovalID = " + i);
        }
        return newApprovalStatus;
    }
}
