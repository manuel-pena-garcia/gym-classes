package org.manuelpenagarcia.gymclasses.web;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuelpenagarcia.gymclasses.service.ActivityService;
import org.manuelpenagarcia.gymclasses.service.MemberService;
import org.manuelpenagarcia.gymclasses.view.controller.MemberController;
import org.manuelpenagarcia.gymclasses.view.dto.ReducedMemberDTO;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
public class MemberControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MemberService memberService;
	
	@MockBean
	private ActivityService activityService;
	
	@Test
	public void whenCallFindAll_shouldReturnListSizeTwo() throws Exception {
		
		Mockito.when(memberService.findAllMembers()).thenReturn(mockMemberList());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/member/findAll").contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
	}
	
	private List<ReducedMemberDTO> mockMemberList() {
		List<ReducedMemberDTO> members = new ArrayList<ReducedMemberDTO>();
		
		ReducedMemberDTO member = new ReducedMemberDTO();
		
		member.setId(1L);
		member.setName("Hulk Hogan");
		
		ReducedMemberDTO member2 = new ReducedMemberDTO();
		
		member2.setId(2L);
		member2.setName("Undertaker");
		
		members.add(member);
		members.add(member2);
		
		return members;
	}
}
