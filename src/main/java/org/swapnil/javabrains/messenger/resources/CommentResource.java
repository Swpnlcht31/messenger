package org.swapnil.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.swapnil.javabrains.messenger.model.Comment;
import org.swapnil.javabrains.messenger.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService commentservice = new CommentService();

	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return commentservice.getAllComments(messageId);
	}

	@POST
	public Comment addMessage(@PathParam("messageId") long messageId, Comment comment) {

		return commentservice.addComment(messageId, comment);
	}

	@PUT
	@Path("/{commentId}")
	public Comment updateMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long id,
			Comment comment) {
		return commentservice.updateComment(messageId, comment);
	}

	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		commentservice.removeComment(messageId, commentId);
	}

	@GET
	@Path("/{commentId}")
	public Comment getMessage(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId) {
		return commentservice.getComment(messageId, commentId);

	}
}
