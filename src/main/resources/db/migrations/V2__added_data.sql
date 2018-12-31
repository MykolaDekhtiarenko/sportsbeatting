INSERT INTO `sportsbeatting`.`sport_event` (`type`, `id`, `end_date`, `start_date`, `title`) VALUES ('FootballSportEvent', '1', '2018-10-27 21:00:00', '2018-10-27 19:00:00', 'Southampton vs Bournemoth'),
('TennisSportEvent', '2', '2018-11-01 21:00:00', '2018-11-01 19:00:00', 'Rafael Nadal vs. Alexander Zverev, Indian Wells 4th Round');


INSERT INTO `sportsbeatting`.`bet` (`id`, `description`, `type`, `sport_event_id`) VALUES ('1', '-', 'WINNER', '1'),
('2', '-', 'BEATING_FOR_GOALS', '1'),
('3', '-', 'WINNER', '2');


INSERT INTO `sportsbeatting`.`outcome` (`id`, `value`, `bet_id`) VALUES ('1', 'Southampton', '1'),
('2', 'Bournemoth', '1'),
('3', 'Draw', '1'),
('4', '0', '2'),
('5', '1', '2'),
('6', '>=2', '2'),
('7', '0', '3'),
('8', '1', '3'),
('9', '>=2', '3');



INSERT INTO `sportsbeatting`.`outcome_odd` (`id`, `valid_from`, `valid_to`, `value`, `outcome_id`) VALUES ('1', '2018-09-27', '2018-09-30', '4', '1'),
('2', '2018-09-30', NULL, '5', '1'),
('3', '2018-09-27', '2018-09-30', '1.7', '2'),
('4', '2018-09-30', NULL, '1.5', '2'),
('5', '2018-09-27', '2018-09-30', '3', '3'),
('6', '2018-09-30', NULL, '3.5', '3'),
('7', '2018-09-27', NULL, '1.75', '4'),
('8', '2018-09-27', NULL, '1.25', '5'),
('9', '2018-09-27', NULL, '1.05', '6');
