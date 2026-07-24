package ticket.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import ticket.model.Ticket;
import ticket.service.ListTicketService;

public class ListTicketHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/ticketList.jsp";
	private ListTicketService listService = new ListTicketService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 정기권 목록 데이터를 불러오기
		List<Ticket> ticketList = listService.getTicketList();

		req.setAttribute("ticketList", ticketList);
		
		// section 영역에 들어갈 페이지 지정
		req.setAttribute("viewPage", FORM_VIEW);

		return "/layout.jsp";
	}
}