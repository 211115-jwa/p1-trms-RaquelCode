@tag
Feature: comments about the status of the  Reimbursement

  @tag1
  Scenario: request additional information from the employee
    
    GivenThe department head must provide approval for Tuition Reimbursement

    When The Department Head can request additional information from the employee
    Then The department head must provide approval for Tuition Reimbursement

  @tag2
  Scenario Outline: The depertment have to send any info
    Given The BenCo must provide approval for Tuition Reimbursement <name> the the requester
    When The BenCo can request additional information from<employee>
    Then Upon confirmation, the amount is awarded to the requestor<status> the request is completed

    Examples: 
      | firstname       | value | status  |
      |                 |     5 | success |
      | last name name2 |     7 | Fail    |
