@calendar
Feature: Check api calendar

  Scenario Outline: Check api get event
    When I want to get event from "<from>" to "<to>"
    Then Check response code when get event by type
    Examples:
      | from          | to            |
      | 1667235600000 | 1669827600000 |

  Scenario Outline: Post for event's schedule type is Once or Weekly or Monthly
    When Create event "<title>","<type>","<scheduleType>"
    Then Check response code when create Once, Weekly, Monthly event
    Then check title of event
    Examples: Set schedule
      | title              | type     | scheduleType |
      | Meeting once 1     | Meeting  | Once         |
      | reminder once 1    | Reminder | Once         |
      | task one 1         | Task     | Once         |
      | meeting weekly 1   | Meeting  | Weekly       |
      | reminder weekly 1  | Reminder | Weekly       |
      | task weekly 1      | Task     | Weekly       |
      | meeting monthly 1  | Meeting  | Monthly      |
      | reminder monthly 1 | Reminder | Monthly      |
      | task monthly 1     | Task     | Monthly      |

  Scenario Outline: Post for event's schedule type is Daily
    When Create daily event "<title>","<type>","<scheduleType>"
    Then Check response code when create daily event
    Examples:
      | title            | type     | scheduleType |
      | meeting daily 1  | Meeting  | Daily        |
      | reminder daily 1 | Reminder | Daily        |
      | task daily 1     | Task     | Daily        |

  Scenario Outline: Check api post for recurrence event
    When Create recurrence event "<title>","<type>","<recurrence>"
    Then Check response code when create recurrence event
    Examples:
      | title         | type     | recurrence                                |
      | reccurrence 1 | Meeting  | FREQ=DAILY;INTERVAL=3;UNTIL=1672333200000 |
      | recurrence 2  | Reminder | FREQ=DAILY;INTERVAL=2;COUNT=2             |

  Scenario Outline: Check api post for event has attendees
    When Create event with "<title>","<type>","<scheduleType>","<attendees_id>","<to_department_ids>","<to_role_ids>","<to_thread_ids>"
    Then Check response code when event has attendees
    Examples:
      | title                       | type    | scheduleType | attendees_id | to_department_ids                | to_role_ids                      | to_thread_ids |
      | Event nay co nguoi tham gia | Meeting | Daily        | 1560603946   | 32a0030baf9c41c68940eee2e59639c3 | 173f8cc0d8d349458a98e344fcf09722 | 1659606654118 |

  Scenario Outline: Edit
    When Edit event to "<title>","<scheduleType>","<value>","<start_hour>","<start_minute>","<end_hour>","<end_minute>","<identities>","<method>"
    Then Check response code when edit event
    Examples:
      | title         | scheduleType | value         | start_hour | start_minute | end_hour | end_minute | identities | method |
      | Ahihi edit ne | Once         | 1669870800000 | 12         | 0            | 12       | 50         |            | ROOT   |


  Scenario: Delete
    When Delete event
    Then Check response code when delete event


