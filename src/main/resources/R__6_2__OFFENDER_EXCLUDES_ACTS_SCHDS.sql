-- OFFENDER_EXCLUDE_ACTS_SCHDS (record of offenders being excluded from scheduled activities)
INSERT INTO OFFENDER_EXCLUDE_ACTS_SCHDS
    (OFFENDER_EXCLUDE_ACT_SCHD_ID, OFFENDER_BOOK_ID, OFF_PRGREF_ID, CRS_ACTY_ID, EXCLUDE_DAY, SLOT_CATEGORY_CODE)
VALUES
    (-1, -4, -7, -4, 'FRI', null),
    (-2, -5, -8, -1, 'MON', 'AM'),
    (-3, -5, -8, -1, 'TUE', 'PM');
