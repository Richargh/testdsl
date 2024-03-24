package de.richargh.testdslcontrol;

import de.richargh.sampleusage.app.AppFloor;
import de.richargh.sampleusage.app.Book;
import de.richargh.sampleusage.app.BookBuilder;
import de.richargh.sampleusage.auth.*;
import de.richargh.sampleusage.kernel.*;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public final class AppTestState {

    private final List<Book> books = new LinkedList<>();
    private final List<User> users = new LinkedList<>();
    private final List<Role> roles = new LinkedList<>();
    private final List<Permission> permissions = new LinkedList<>();

    private final Clock clock;
    private final DataFabrications fabrications;
    private final Ids ids;

    public AppTestState(Clock clock, DataFabrications fabrications, Ids ids) {
        this.clock = clock;
        this.fabrications = fabrications;
        this.ids = ids;
    }

    public void saveTo(AppFloor floor) {
        // written with lambda so repository only gets created when actually needed
        books.forEach(it -> floor.books().add(it));
        permissions.forEach(it -> floor.permissions().add(it));
        roles.forEach(it -> floor.roles().add(it));
        users.forEach(it -> floor.users().add(it));
    }

    public Book book(Consumer<BookBuilder> action) {
        return stash(new BookBuilder(clock, fabrications, ids), action, books);
    }

    public Book book() {
        return book(it -> {
        });
    }

    public UserCombo userCombo(Consumer<UserComboBuilder> action) {
        return stashTo(new UserComboBuilder(clock, fabrications, ids), action, it -> {
            users.add(it.user());
            roles.add(it.role());
            permissions.addAll(it.permissions());
        });
    }

    public UserCombo userCombo() {
        return userCombo(it -> {
        });
    }

    public User user(Consumer<UserBuilder> action) {
        return stash(new UserBuilder(clock, fabrications, ids), action, users);
    }

    public User user() {
        return user(it -> {
        });
    }

    public Role role(Consumer<RoleBuilder> action) {
        return stash(new RoleBuilder(clock, fabrications, ids), action, roles);
    }

    public Role role() {
        return role(it -> {
        });
    }

    public Permission permission(Consumer<PermissionBuilder> action) {
        return stash(new PermissionBuilder(clock, fabrications, ids), action, permissions);
    }

    public Permission permission() {
        return permission(it -> {
        });
    }

    private static <TId extends EntityId,
            TEntity extends Entity<TId>,
            Builder extends TestBuilder<TEntity>>
    TEntity stash(
            Builder builder,
            Consumer<Builder> action,
            List<TEntity> stash) {
        action.accept(builder);
        var entity = builder.build();
        stash.add(entity);
        return entity;
    }

    private static <Element,
            Builder extends TestBuilder<Element>>
    Element stashTo(
            Builder builder,
            Consumer<Builder> init,
            Consumer<Element> stash) {
        init.accept(builder);
        var entity = builder.build();
        stash.accept(entity);
        return entity;
    }

}
