package com.belrose.agreservice.controller;

import com.belrose.agreservice.constants.Uri;
import com.belrose.agreservice.model.AgreResponse;
import com.belrose.agreservice.service.AgreService;
import com.belrose.agreservice.model.Agre;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping(Uri.URL_AGRE)
public class AgreController {

  private final AgreService agreService;

  public AgreController(AgreService agreService) {
    this.agreService = agreService;
  }

  @Operation(summary = "Save an agre",description = "Save an agre")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200",description = "Save agre by status return",content = @Content),
          @ApiResponse(responseCode = "400",description = "Invalid/Bad request",content = @Content),
          @ApiResponse(responseCode = "401",description = "Unauthorized",content = @Content),
          @ApiResponse(responseCode = "403",description = "Forbidden",content = @Content),
          @ApiResponse(responseCode = "404",description = "Location not found",content = @Content),
          @ApiResponse(responseCode = "405",description = "Method not allowed",content = @Content),
          @ApiResponse(responseCode = "408",description = "HTTP timeout exception",content = @Content),
          @ApiResponse(responseCode = "415",description = "Unsupported Media type",content = @Content),
          @ApiResponse(responseCode = "429",description = "API calls exceeded. Please wait",content = @Content),
          @ApiResponse(responseCode = "500",description = "Internal Application error",content = @Content),
          @ApiResponse(responseCode = "503",description = "Service Unavailable",content = @Content)
  })
  @PostMapping(path = Uri.URI_SAVE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public AgreResponse saveAgre(@RequestBody @Valid Agre agre) {
     return agreService.saveAgreAndAgreEncoded(agre);
  }

}
