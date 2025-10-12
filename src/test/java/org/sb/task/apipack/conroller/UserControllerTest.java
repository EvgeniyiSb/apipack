package org.sb.task.apipack.conroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.sb.task.apipack.controller.UserController;
import org.sb.task.apipack.model.User;
import org.sb.task.apipack.repository.UserRepository;
import org.sb.task.apipack.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(UserController.class)
@WebMvcTest
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
//
//    @MockitoBean
    @Mock
    private UserServiceImpl userService;

    @MockitoBean
    private UserRepository userRepository;

//    @Autowired
//    private UserRepository userRepository;

    private User user;

    @InjectMocks
    private UserController userController;

    private static final int TEST_ID = 1;
    private static final String TEST_USERNAME = "Борис";
    private static final String TEST_EMAIL = "testmail@test.ts";
    private static final int TEST_AGE = 23;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        user = User.builder()
                .name(TEST_USERNAME)
                .email(TEST_EMAIL)
                .age(TEST_AGE)
                .build();
    }

//    @AfterEach
//    void resetDB(){
//        userRepository.deleteAll();
//    }

//    private final ObjectMapper objectMapper = new ObjectMapper();

//    @Test
    public void createUser() throws Exception{
//
////        int userId = createTestUser(TEST_USERNAME, TEST_EMAIL, TEST_AGE).getId();
//
//        User user1 = new User(TEST_USERNAME, TEST_EMAIL, TEST_AGE);
//
////        ResponseEntity<User> result =
////
////                mockMvc.perform(put("/user", user, User.class));
//
////        MockHttpServletResponse result = mockMvc.perform(put("/user")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(mapper.writeValueAsString(user)))
////                .andReturn()
////                .getResponse();
//////                .andExpect(status().isCreated());
//
////        assertThat(result.getStatus());
//
////        assertThat();
////        assertThat(result.getStatus()).isEqualTo(status().isCreated());
////        assertThat().isEqualTo(HttpStatus.CREATED.value());
//
////        assertThat(result.getStatusCode()).isEqualTo(status().isCreated());
//        mockMvc.perform(
//                        post("/user")
//                                .content(mapper.writeValueAsString(user1))
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(status().isCreated());
//
//
////        mockMvc.perform(post("/user"))
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(mapper.writeValueAsString(user1))
////
////                .andExpect(status().isCreated());
    }

//    @Test
//    public void updateUser() throws Exception{
////        int userId = createTestUser(TEST_USERNAME, TEST_EMAIL, TEST_AGE).getId();
//
////        int userId = this.userService.create(new User(TEST_USERNAME, TEST_EMAIL, TEST_AGE)).getId();
//
//        when(userService.create(new User(TEST_USERNAME, TEST_EMAIL, TEST_AGE))).thenReturn(user);
//
//        when(userService.update(TEST_ID, user)).thenReturn(user);
//
//        mockMvc.perform(put("/user/{id}", user.getId())
//                .content(mapper.writeValueAsString(new User("ааа", TEST_EMAIL, TEST_AGE).setId()))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//
//                .andExpect(status().isOk());
//    }


    @Test
    public void readExistingUser_status200AndComparisonValues() throws Exception{
//        when(userService.read(TEST_ID)).thenReturn(user);

//        ResponseEntity<String> response = restTemplate.getForEntity("/books", String.class);

//        org.sb.task.apipack.model.User user1 = createTestUser(TEST_USERNAME, TEST_EMAIL, TEST_AGE);

//        when(userRepository.save(any(User.class))).thenReturn(user1);

//        mockMvc.perform(post("/user")
//                        .content(mapper.writeValueAsString(user1))
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());


        long id = createTestUser(TEST_USERNAME, TEST_EMAIL, TEST_AGE).getId();
        mockMvc.perform(
                        get("/user/{id}", id))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.id").value(id))
//                .andExpect(jsonPath("$.name").value("Michail"));


//        mockMvc.perform(
//                get("/user/{id}", 82)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
////                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(1));





//        verify(userRepository, times(1)).save(any(User.class));

////        mockMvc.perform(get("/user/{id}", TEST_ID))
////        mockMvc.perform(get("/user/{id}", userId))
//        mockMvc.perform(get("/user/{id}", 82))
//                .andExpect(status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(TEST_USERNAME))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(TEST_EMAIL))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(TEST_AGE));
//;
////        System.out.println("My Result" + result.getResponse().getContentAsString());

    }

//    @Test
    public void readNodExistingUser_status404() throws Exception {
        mockMvc.perform(get("/user/{id}", TEST_ID))
                .andExpect(status().isNotFound());
    }

//    @Test
    public void deleteExistUser_status200AndComparisonUserId() throws Exception{
//        when(userService.read(TEST_ID)).thenReturn(user);

        mockMvc.perform(delete("/user/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()));
    }

//    @Test
//    public void deleteNotExistUser_status304() throws Exception {
//
//        User user1 = createTestUser(TEST_USERNAME, TEST_EMAIL, TEST_AGE);
//
////        when(userService.read(TEST_ID)).thenReturn(user1);
//
//        mockMvc.perform(delete("/user/{id}", 1000))
//                .andExpect(status().isNotModified());
//    }



    private User createTestUser(String name, String email, int age){
        User userTest = new User(name, email, age);

        return userRepository.save(userTest);
    }

//    @Test
    public void fff() throws Exception{
//        User user1 = new User(TEST_USERNAME, TEST_EMAIL, TEST_AGE);
        when(createTestUser(TEST_USERNAME, TEST_EMAIL, TEST_AGE).getName()).thenReturn(user.getName());

//        doReturn(createTestUser(TEST_USERNAME, TEST_EMAIL, TEST_AGE)).when(user1).getId();
//
//        doThrow(user1).when(createTestUser(TEST_USERNAME, TEST_EMAIL, TEST_AGE));

//        doReturn(user1).when(createTestUser(TEST_USERNAME, TEST_EMAIL, TEST_AGE)).getId();


        mockMvc.perform(
                        post("/user")
                                .content(mapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
//                .andExpect(content().json(mapper.writeValueAsString(user1)));
    }

}