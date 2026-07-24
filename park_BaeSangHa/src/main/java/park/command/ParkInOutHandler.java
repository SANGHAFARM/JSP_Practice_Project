package park.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import park.model.Park;
import park.service.ParkInOutService;
import ticket.model.Ticket;

public class ParkInOutHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/parkInOutForm.jsp";
	private static final String IN_SUCCESS_VIEW = "/WEB-INF/view/parkInSuccess.jsp";
	private static final String OUT_SUCCESS_VIEW = "/WEB-INF/view/parkOutSuccess.jsp";

	private ParkInOutService parkService = new ParkInOutService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String action = req.getParameter("action");

		// 입고 확정 처리
		if ("inboundSubmit".equalsIgnoreCase(action)) {
			return processInboundSubmit(req, res);
		}

		// 출고 확정 처리
		if ("outboundSubmit".equalsIgnoreCase(action)) {
			return processOutboundSubmit(req, res);
		}

		// 버튼 클릭에 따른 조회
		String type = req.getParameter("type");
		String carno = req.getParameter("carno");

		if (carno != null && !carno.trim().isEmpty()) {
			carno = carno.trim();

			if ("in".equals(type)) {
				// 주차입고 조회
				int status = parkService.checkInboundStatus(carno);
				if (status == 2) {
					req.setAttribute("msg", "이미 입고된 차량입니다");
				} else if (status == 3) {
					req.setAttribute("msg", "정기권에 해당하는 차량이 없습니다");
				} else if (status == 4) {
					req.setAttribute("msg", "사용 불가(정지/만료) 상태의 정기권입니다");
				} else {
					Ticket ticket = parkService.getTicketByCarno(carno);
					req.setAttribute("ticketInfo", ticket);
					req.setAttribute("mode", "in");
				}
			} else if ("out".equals(type)) {
				// 주차출고 조회
				Park park = parkService.getParkedCar(carno);
				if (park == null) {
					req.setAttribute("msg", "입고된 차량이 아닙니다");
				} else {
					req.setAttribute("parkInfo", park);
					req.setAttribute("mode", "out");
				}
			}
		}

		req.setAttribute("viewPage", FORM_VIEW);
		return "/layout.jsp";
	}

	// 입고 DB 저장
	private String processInboundSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String carno = req.getParameter("carno");
		String grade = req.getParameter("grade");

		Park park = new Park(0, carno, grade, "1", null, null);
		parkService.processInbound(park);

		req.setAttribute("msgType", "inbound");
		req.setAttribute("viewPage", IN_SUCCESS_VIEW);
		return "/layout.jsp";
	}

	// 출고 DB 저장
	private String processOutboundSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String carno = req.getParameter("carno");

		boolean success = parkService.processOutbound(carno);

	    if (success) {
	        req.setAttribute("msgType", "outbound");
	        req.setAttribute("viewPage", OUT_SUCCESS_VIEW);
	    } else {
	        req.setAttribute("msg", "이미 출고되었거나 입고 내역이 존재하지 않습니다.");
	        req.setAttribute("viewPage", FORM_VIEW);
	    }
	    
	    return "/layout.jsp";
	}
}