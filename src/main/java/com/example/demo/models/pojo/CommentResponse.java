package com.example.demo.models.pojo;

import com.example.demo.models.dto.BlogDTO;
import com.example.demo.models.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    List<CommentDTO> commentResponse;
}
