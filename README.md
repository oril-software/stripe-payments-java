## Stripe Payments integration with Spring Boot

Code sample in this repository demonstrates how to easily integrate payments processing
into your Spring Boot application using Stripe API.

Stripe is one of the best and most popular software platforms for running an internet 
business. But the main reason major tech companies love Stripe is its developer tools. 
It provides the easiest and fastest way to integrate with your application, no matter what it is - web, mobile or desktop.


### Prerequisites 
* Java 8+
* Maven ^3.6.0
* Stripe Account

### Configuration
To use this code sample you need to change the **Stripe.apiKey** 
in **__StripeClient.java__** file according to your Stripe **__secret key__**:


### How to Run
* From IDEA: run the **__MainApplication.class__**
* From CLI: run command `mvn spring-boot:run` 

### Usage Guide & Tips
First of all we need to retrieve token from Stripe. Use `/payments/token` endpoint.

**IMPORTANT**: it's highly recommend to retrieve this token on the front-end side using Stripe form.
In this code snippet this method is used only for demonstration purpose of how to use it on the backend side.
This token is only for one-time usage. Can be used for charging credit card or for creating customer with source credit card.

Then we can you this **__token__** to charge Credit Card or we can create **__Customer__** 
with this source Credit Card and then charge this customer every time he purchases something.

**__IMPORTANT__**: amount to charge should be provided in cents. And minimum amount the Stripe accepts is 50 cents. 

### Community
* Please send us your suggestions on how we make this code even more useful for the development community or contribute to this repo!
* Check out our [blog](https://oril.co/blog) with more articles!”