package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleNotFoundException;
import article.service.DeleteArticleService;
import article.service.PermissionDeniedException;
import auth.service.User;
import mvc.command.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {

    private DeleteArticleService deleteService = new DeleteArticleService();

    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        User authUser = (User) req.getSession().getAttribute("authUser");
        String noVal = req.getParameter("no");
        int no = Integer.parseInt(noVal);

        try {
            deleteService.delete(no, authUser.getId());

            res.sendRedirect(req.getContextPath() + "/article/list.do");
            return null;

        } catch (ArticleNotFoundException e) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        } catch (PermissionDeniedException e) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }
}