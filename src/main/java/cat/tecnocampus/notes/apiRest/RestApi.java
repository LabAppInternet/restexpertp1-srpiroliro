package cat.tecnocampus.notes.apiRest;

import cat.tecnocampus.notes.application.DTOs.NoteLabDTO;
import cat.tecnocampus.notes.application.DTOs.UserLabDTO;
import cat.tecnocampus.notes.application.NotesService;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;


@Tag(name = "Expert", description = "Notes management API")
@RestController
public class RestApi {
    private NotesService notesService;

    public RestApi(NotesService notesService) {
        this.notesService = notesService;
    }


    @Operation(
        summary = "Get a list of all users",
        description = "Returns a list of all users in the system",
        tags = { "users", "get" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserLabDTO.class), mediaType = "application/json") }),
    })
    @GetMapping("/users")
    public List<UserLabDTO> getAllUsers() {
        return notesService.getAllUsers();
    }

    @Operation(
        summary = "Retrieve a User by email",
        description = "Get a User object by specifying its email. The response is UserLabDTO object with ...",
        tags = { "users", "get" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserLabDTO.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/users/{email}")
    public UserLabDTO getUser(@PathVariable String email) {
        return notesService.getUserLab(email);
    }


    @Operation(
        summary = "Get notes user owns",
        description = "Get a list of notes that the user owns by specifying its email. The response is List<NoteLabDTO> object with ..",
        tags = { "notes", "get" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserLabDTO.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/users/{email}/ownedNotes")
    public List<NoteLabDTO> getUserOwnedNotes(@PathVariable String email) {
        return notesService.getOwnedNotes(email);
    }


    @Operation(
        summary = "Retrieve all notes that the user can edit",
        description = "Get a list of notes that the user can edit by specifying its email. The response is List<NoteLabDTO> object with ...",
        tags = { "notes", "get" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = List.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/users/{email}/allowedEditNotes")
    public List<NoteLabDTO> getUserAllowedEditNotes(@PathVariable String email) {
        return notesService.getAllowedEditNotes(email);
    }

    @Operation(
        summary = "Create a new note",
        description = "Create a new note by specifying users email. The response is UserLabDTO object with ...",
        tags = { "notes", "post" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/users/{email}/notes")
    public void createNewNote(@PathVariable String email, @RequestBody NoteLabDTO notelab) {
        notesService.createNewNote(email, notelab.getTitle(), notelab.getContent());
    }


    @Operation(
        summary = "Edit a users note",
        description = "Edit a users note by specifying its email and title. The response is UserLabDTO object with ...",
        tags = { "notes", "put" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema =@Schema()) }),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/users/{email}/notes")
    public void editNote(@PathVariable String email, @RequestBody NoteLabDTO notelab) {
        notesService.editNote(email, notelab.getTitle(), notelab.getContent());
    }


    @Operation(
        summary = "Create a new user",
        description = "Create a User object by specifying its email. The response is UserLabDTO object with ...",
        tags = { "users", "post" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/users")
    public void createNewUser(@RequestBody UserLabDTO userLab) {
        notesService.createNewUser(userLab);
    }


    @Operation(
        summary = "Allow a user to edit a note",
        description = "Allow a user to edit a note by specifying its email and title.",
        tags = { "notes", "put" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/users/{ownerEmail}/allowed/{allowedUserEmail}/{title}")
    public void allowEdit(@PathVariable String ownerEmail, @PathVariable String allowedUserEmail, @PathVariable String title) {
        notesService.allowEditNoteToUser(ownerEmail, allowedUserEmail, title);
    }


    @Operation(
        summary = "Retrieve a User by email",
        description = "Get a User object by specifying its email. The response is UserLabDTO object with ...",
        tags = { "users", "delete" })
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/users/{email}/notes/{title}")
    public void deleteNote(@PathVariable String email, @PathVariable String title) {
        notesService.deleteNote(email, title);
    }
}
