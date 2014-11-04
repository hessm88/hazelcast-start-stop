/**
 * <h1>FamilySearch Change-Routes Service</h1>
 *
 * This provides a basic healthcheck web-service wrapper around the
 * change.entry queue camel routes.
 * This route listens for messages on the change queue, using a
 * JMS selector to route the message to different routes within
 * this service (e.g. discussion, memories)
 */
@XmlSchema(
  namespace = "http://hazelcast-start-stop/v1",
  elementFormDefault = XmlNsForm.QUALIFIED
) package hazelcast.startstop.webapp;

import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlNsForm;
