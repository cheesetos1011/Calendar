@calendar
Feature: Check api calendar

  Scenario Outline: Check api get event
    When I want to get event from "<from>" to "<to>"
    Then Check response code when get event by type
    Examples:
      | from          | to            |
      | 1667235600000 | 1669827600000 |

  Scenario Outline: Post for event's schedule type is Once or Weekly or Monthly
    When Create event "<title>","<type>","<scheduleType>","<description>","<remindBefore>"
    Then Check response code and Verify title, start, end time, description, remind time
    Examples:
      | title              | type     | scheduleType | description | remindBefore |
      | Meeting once 1     | Meeting  | Once         | hihihi      | -300000      |
      | reminder once 1    | Reminder | Once         | abcd        | -600000      |
      | task one 1         | Task     | Once         | 123         | 300000       |
      | meeting weekly 1   | Meeting  | Weekly       |             | 600000       |
      | reminder weekly 1  | Reminder | Weekly       |             | 900000       |
      | task weekly 1      | Task     | Weekly       |             | -900000      |
      | meeting monthly 1  | Meeting  | Monthly      |             | 0            |
      | reminder monthly 1 | Reminder | Monthly      |             | 0            |
      | task monthly 1     | Task     | Monthly      |             | 0            |

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

#  Scenario Outline: Check api post for event has attendees
#    When Create event with "<title>","<type>","<scheduleType>","<attendees_id>","<to_department_ids>","<to_role_ids>","<to_thread_ids>"
#    Then Check response code when event has attendees
#    Examples:
#      | title                       | type    | scheduleType | attendees_id | to_department_ids                | to_role_ids                      | to_thread_ids |
#      | Event nay co nguoi tham gia | Meeting | Daily        | 1560603946   | 32a0030baf9c41c68940eee2e59639c3 | 173f8cc0d8d349458a98e344fcf09722 | 1659606654118 |

#    room_id, has_meeting
  Scenario Outline: Check api post when create room event
    When create room event "<type>","<scheduleType>","<hasMeeting>"
    Then check response code and verify room_id, has_meeting
    Examples:
      | type    | scheduleType | hasMeeting |
      | Meeting | Once         | true        |
      | Meeting | Weekly       | false       |

  Scenario Outline: Edit
    When Edit event to "<title>","<scheduleType>","<value>","<start_hour>","<start_minute>","<end_hour>","<end_minute>","<identities>","<method>"
    Then Check response code when edit event
    Examples:
      | title         | scheduleType | value         | start_hour | start_minute | end_hour | end_minute | identities | method |
      | Ahihi edit ne | Once         | 1669870800000 | 12         | 0            | 12       | 50         |            | ROOT   |


  Scenario: Delete
    When Delete event
    Then Check response code when delete event


