package io.zeebe.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import io.zeebe.test.EmbeddedBrokerRule;

@Ignore("adapt for eventdriven")
public class ZeebeComponentTest extends CamelTestSupport {

    @Rule
    public final EmbeddedBrokerRule broker = new EmbeddedBrokerRule();

    @Test
    public void testZeebe() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(1);

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("zeebe://foo")
                  .to("zeebe://bar")
                  .to("mock:result");
            }
        };
    }
}