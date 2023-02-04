package com.example.demo.models.pojo;

import com.example.demo.models.dto.BlogDTO;
import com.example.demo.models.entity.Comment;
import com.example.demo.models.entity.Tags;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.TabableView;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {

  List<BlogDTO> blogResponse;

}
