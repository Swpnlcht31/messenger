package org.swapnil.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.model.ParamQualifier;
import org.swapnil.javabrains.messenger.model.Message;
import org.swapnil.javabrains.messenger.resources.beans.MessageFilterBean;
import org.swapnil.javabrains.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessagesResources {
	MessageService messageservice = new MessageService();

	/*
	 * @GET public List<Message> getMessages(@QueryParam("year") int
	 * year, @QueryParam("start") int start,
	 * 
	 * @QueryParam("size") int size) { if (year > 0) { return
	 * messageservice.getAllMessagesForYear(year); } if (start >= 0 && size >= 0) {
	 * 
	 * return messageservice.getAllMessagesPaginated(start, size); }
	 * 
	 * return messageservice.getAllMessage();
	 * 
	 * }
	 */
	@GET
	public List<Message> getMessage(@BeanParam MessageFilterBean filterbean) {
		if (filterbean.getYear() > 0) {
			return messageservice.getAllMessagesForYear(filterbean.getYear());
		}
		if (filterbean.getStart() >= 0 && filterbean.getStart() >= 0) {
			return messageservice.getAllMessagesPaginated(filterbean.getStart(), filterbean.getSize());
		}
		return messageservice.getAllMessage();
	}

	@POST
	public Message addMessage(Message message) {
		return messageservice.addMessage(message);
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageservice.updateMessage(message);
	}

	@DELETE
	@Path("/{message}")
	public void deleteMessage(@PathParam("messageid") long id) {
		messageservice.removeMessage(id);
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		Message message = messageservice.getMessage(id);
		String uri = uriInfo.getBaseUriBuilder().path(MessagesResources.class).path(Long.toString(message.getId()))
				.build().toString();
		message.addLink(uri, "self");
		return message;
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
