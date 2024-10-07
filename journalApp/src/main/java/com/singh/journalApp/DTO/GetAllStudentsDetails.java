package com.singh.journalApp.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetAllStudentsDetails {
    private List<GetStudentResponse> studentDetailsList = new ArrayList<>();
    private int totalPages;
    private int currentPage;
    private long totalElements;
}
