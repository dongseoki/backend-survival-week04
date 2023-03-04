package kr.megaptera.assignment.domain.post;

import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {
  @Autowired
  private MockMvc mockMvc;

  //  @Autowired
  @SpyBean
  PostRepository postRepository;

  @Test
  public void create() throws Exception {
    String json = """
      {
        "title" : "제목입니다",
        "author" : "작가입니다",
        "content" : "제목입니다"
      }
      """;

    int oldSize = postRepository.getPosts().size();
    this.mockMvc.perform(post("/posts").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    int newSize = postRepository.getPosts().size();

    verify(postRepository).addPost(argThat(postCreateRequestDto -> {
      return postCreateRequestDto.getTitle().equals("제목입니다");
    }));
    assertThat(newSize).isEqualTo(oldSize + 1);
  }


}
