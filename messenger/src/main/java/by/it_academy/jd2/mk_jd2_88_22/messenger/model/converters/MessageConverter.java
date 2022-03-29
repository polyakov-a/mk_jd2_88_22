package by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters;

import by.it_academy.jd2.mk_jd2_88_22.messenger.entity.MessageEntity;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.Message;
import by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.api.IConverter;

public class MessageConverter implements IConverter<Message, MessageEntity> {

    private final UserConverter converter = new UserConverter();

    @Override
    public Message convertToDTO(MessageEntity entity) {
        if (entity == null) {
            return null;
        } else {
            Message message = Message.Builder.build()
                    .setRecipient(this.converter.convertToDTO(entity.getRecipient()))
                    .setSender(this.converter.convertToDTO(entity.getSender()))
                    .setMessage(entity.getMessage())
                    .setDate(entity.getDate())
                    .createMessage();
            message.setId(entity.getId());
            return message;
        }
    }

    @Override
    public MessageEntity convertToEntity(Message message) {
        if (message == null) {
            return null;
        } else {
            return MessageEntity.Builder.build()
                    .setRecipient(this.converter.convertToEntity(message.getRecipient()))
                    .setSender(this.converter.convertToEntity(message.getSender()))
                    .setMessage(message.getMessage())
                    .setDate(message.getDate())
                    .createMessage();
        }
    }
}
