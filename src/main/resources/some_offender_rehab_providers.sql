INSERT INTO REHABILITATION_PROVIDERS
    (PROVIDER_CODE, DESCRIPTION, PROVIDER_TYPE, LIST_SEQ, ACTIVE_FLAG, EXPIRY_DATE)
VALUES
    ('ProvCode', 'Test provider of rehabilitation services', 'EXEC', 1, 'Y', '2058-05-09 13:23:36'),
    ('ProvCode2', 'Another test provider of rehabilitation services', 'EXEC', 2, 'N', '2058-05-09 13:23:36');

INSERT INTO OFFENDER_REHAB_PROVIDERS
    (OFFENDER_REHAB_PROVIDER_ID, OFFENDER_REHAB_DECISION_ID, PROVIDER_CODE, ACTIVE_FLAG, COMMENT_TEXT)
VALUES
    (1, 2, 'ProvCode', 'N', 'Comments'),
    (2, 2, 'ProvCode', 'Y', 'Comments'),
    (3, 2, 'ProvCode', 'N', 'Comments');