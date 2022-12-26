package ru.alexey.contactbook.contactbookback.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ContactsDetailsService {
}
