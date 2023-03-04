package kr.megaptera.assignment.domain.post;

import kr.megaptera.assignment.dtos.PostCreateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostFeatureTest {
  @Value("${local.server.port}")
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void post() {
    String url = "http://localhost:" + port + "/posts";
    PostCreateRequestDto postCreateRequestDto =
      new PostCreateRequestDto("제목입니다", "작가입니다.", "내용입니다.");
    restTemplate.postForLocation(url, postCreateRequestDto);
    String body = restTemplate.getForObject(url, String.class);
    Assertions.assertThat(body).contains("제목입");
    Assertions.assertThat(body).contains("내용입");
  }
}
