# mobiquity
For this task, imagine you are a part of the team that performs quality
assurance for a user blog, the frontend design is not yet developed, but the
API has already been published here:
https://jsonplaceholder.typicode.com/.
You can help the developers on your team make the feature more
robust, by helping them write tests for some workflows that they might
break while making progress with developing business logic. The task is:
1. To create a test automation framework skeleton.
2. To perform the validations for the comments for the post made by a
specific user.

Flow to be tested

1. Search for the user with username “Delphine”.
2. Use the details fetched to make a search for the posts written by the
user.
3. For each post, fetch the comments and validate if the emails in the
comment section are in the proper format.
4. Think of various scenarios for the test workflow, all the things that
can go wrong. Add them to test coverage


CI execution completed by using CircleCI tool that is integrated with GitHub repository.
