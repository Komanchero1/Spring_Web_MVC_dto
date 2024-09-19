    Домашнее задание по теме "Spring Web MVC".

    Задача со звездочкой.

    В класс Post дабавил поле removed чтобы помечать посты на удаление.
    В классе PostService поменялась логика 

    метод findAll 
    - получает список постов из репозитория
    - фильтрует посты с пометкой removed
    - возвращает список постов без флага removed
  
    метод findById
    - получает пост по Id
    - проверяет помечен ли пост флагом если пост не найден или помечен флагом как удаленный выбрасывает ошибку что пост не найден.
    - 
    метод save
    - проверяет есть пометка у поста как удаленного или нет , если да выбрасывает исключение.
    если пометки нет то метод возвращает обновленный обьект поста.
    Метод предотвращает операции над удаленными постами.

    Изменен класс PostController.

    метод all
    - делает запрос к сервису на получение всех постов 
    - преобразует Post объекты в объекты DTO которые будут содержать только ту инфу которую мы хотим вернуть клиенту
    - создается список DTO объектов
    - возвращает список DTO объектов клиенту
  
    метод getById
    - Аннотация @GetMapping("/{id}") указывает, что данный метод будет вызываться при получении GET-запроса к URL-адресу, который заканчивается на определённый идентификатор поста
    - Аннотация @PathVariable Long id извлекает значение идентификатора id из URL и автоматически преобразует его в тип Long
    - обращается к сервисному слою с указанным id, если поста с таким id нет выбрасывает исключение,если пост есть то формируется объект DTO  с необходимыми данными
    - отправляется сформированный пост клиенту

     метод save
     -Аннотация @PostMapping("/") указывает, что этот метод будет вызываться при получении POST-запроса к URL-адресу, который соответствует корневому пути контроллера
     -Аннотация @RequestBody PostDTO postDTO позволяет извлекать тело запроса, реализованное в формате JSON, и автоматически десериализовать его в объект класса PostDTO
     -cоздаётся новый объект типа Post с использованием данных из postDTO
     - вызывает сервис для сохранения созданного объекта Post
     - создаёт экземпляр PostDTO из вновь сохраненного объекта Post
     - возвращает объект PostDTO, который будет автоматически сериализован в JSON и отправлен обратно клиенту в качестве ответа на запрос

    метод renoveById
    -Аннотация @DeleteMapping("/{id}") указывает, что метод будет вызываться при получении DELETE-запроса к URL, который содержит идентификатор поста
    -Параметр @PathVariable Long id позволяет извлечь значение идентификатора из URI запроса
    -вызывает сервис для поиска поста по переданному идентификатору
    -После  нахождения поста, устанавливается флаг "удален". Это позволяет пометить пост как удалённый, вместо удаления из базы данных
    - сохраняется данный пост с пометкой удален

    В класс NotFoundException добавлена аннотация @ResponseStatus для установки статус-кода 404


       Обоснование, почему вы реализовали логику именно в том слое, в котором дан результат.

       Потому что  контроллер позволяет быстро изменить логику обработки ошибок без необходимости изменения кода в других слоях приложения.Логика проверки наличия объекта в данном случае соответствует его задаче—обрабатывать запросы и возвращать правильные ответы клиенту
